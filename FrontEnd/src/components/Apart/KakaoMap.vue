<template>
  <div class="map_wrap">
    <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden"></div>

    <ApartSearch @changeApartList="changeApartList" @selectListItem="selectListItem" :select_marker="select_marker" />
    <ApartInfo
      :apart_data="select_apart_info[select_marker]"
      @openRoadView="openRoadView"
      v-if="!(select_apart_info[select_marker] === undefined)"
    />

    <CategoryInfo :select_marker_data="select_apart_info[select_marker]" @res_local_data="res_local_data" />

    <v-card id="roadview_card" width="40%" height="60%" v-show="close_loadview_toggle">
      <v-btn id="close_btn" @click="closeRoadViewEvent"><v-icon>mdi-close-box</v-icon></v-btn>
      <div id="roadview_main"></div>
    </v-card>
  </div>
</template>

<script>
import ApartInfo from "@/components/Apart/ApartInfoForm.vue";
import ApartSearch from "@/components/Apart/ApartSearchForm.vue";
import CategoryInfo from "@/components/Apart/CategoryInfo.vue";
export default {
  name: "KakaoMap",
  components: {
    ApartInfo,
    ApartSearch,
    CategoryInfo,
  },
  data() {
    return {
      map: "",
      marker: [],
      select_marker: undefined,
      select_apart_info: [],
      open_infowindow: undefined,
      local_data: [],
      circle: undefined,
      close_loadview_toggle: false,

      roadview: undefined,
      roadviewClient: undefined,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_kakaomapkey;
      document.head.appendChild(script);
    }
  },
  methods: {
    //맵초기 불러오는 함수
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      let roadviewContainer = document.getElementById("roadview_main"); //로드뷰를 표시할 div
      this.roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      this.roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    },

    //아파트 간략소개 (computed 안되는 이유 확인필요)
    apartFullName(apart) {
      return `${apart.roadName} ${apart.dong} ${apart.apartmentName}`;
    },
    //아파트 검색리스트 수정
    changeApartList(data) {
      if (this.open_infowindow != undefined) {
        this.open_infowindow.close();
      }
      for (let idx = 0; idx < this.marker.length; idx++) {
        this.marker[idx].setMap(null);
      }
      this.marker = [];
      this.select_apart_info = data.apart_data;
      for (let i = 0; i < data.lat_lng.length; i++) {
        let moveLatLon = new kakao.maps.LatLng(data.lat_lng[i].lat, data.lat_lng[i].lng);
        let marker = new kakao.maps.Marker({
          position: moveLatLon,
          clickable: true,
        });

        this.marker.push(marker);
        this.marker[i].setMap(this.map);
        kakao.maps.event.addListener(marker, "click", () => {
          if (this.open_infowindow != undefined) {
            this.open_infowindow.close();
          }
          this.open_infowindow = new kakao.maps.InfoWindow({
            content: `<div style="padding:10px;height:50px;width:200px;">${this.apartFullName(
              this.select_apart_info[i]
            )}</div>`,
            removable: true,
          });
          this.open_infowindow.open(this.map, this.marker[i]);
          this.selectMarker(i);
        });
      }
    },
    //마커, 아파트 선택과 선택사항에 따른 처리
    selectMarker(i) {
      if (this.select_marker == i) {
        this.select_marker = undefined;
      } else {
        this.select_marker = i;
      }
    },
    //선택한 아파트의마커idx값과 그에 따른 맵이동
    selectListItem(i) {
      this.select_marker = i;
      this.MoveMap();
    },
    //좌표에 따른 맵이동
    MoveMap() {

      if (this.marker[this.select_marker] != undefined) {
        this.map.panTo(this.marker[this.select_marker].getPosition());
      }
    },

    //편의시설 데이터 불러와서 세팅하는 함수
    res_local_data(local_data) {
      if (this.circle != undefined) {
        this.circle.setMap(null);
      }
      this.circle = new kakao.maps.Circle({
        center: this.marker[this.select_marker].getPosition(), // 원의 중심좌표 입니다
        radius: 300, // 미터 단위의 원의 반지름입니다
        strokeWeight: 5, // 선의 두께입니다
        strokeColor: "#75B8FA", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "dashed", // 선의 스타일 입니다
        fillColor: "#CFE7FF", // 채우기 색깔입니다
        fillOpacity: 0.7, // 채우기 불투명도 입니다
      });
      this.circle.setMap(this.map);

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png",
        imageSize = new kakao.maps.Size(32, 32),
        imageOption = { offset: new kakao.maps.Point(27, 69) };

      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

      for (let i = 0; i < this.local_data.length; i++) {
        this.local_data[i].setMap(null);
      }
      this.local_data = [];
      for (let i = 0; i < local_data.length; i++) {
        let moveLatLon = new kakao.maps.LatLng(local_data[i].y, local_data[i].x);
        let marker = new kakao.maps.Marker({
          position: moveLatLon,
          clickable: true,
          image: markerImage,
        });
        this.local_data.push(marker);
        this.local_data[i].setMap(this.map);

        let local_infowindow = new kakao.maps.InfoWindow({
          content: `<div style="padding:10px;height:50px;width:200px;">${local_data[i].place_name}</div>`,
          removable: true,
        });

        kakao.maps.event.addListener(marker, "mouseover", () => {
          local_infowindow.open(this.map, this.local_data[i]);
        });
        kakao.maps.event.addListener(marker, "mouseout", () => {
          local_infowindow.close();
        });
      }
    },

    //로드뷰 닫기
    closeRoadViewEvent() {
      this.close_loadview_toggle = false;
    },

    //로드뷰 보여주기
    openRoadView(lat_lng) {
      this.close_loadview_toggle = true;
      let position = new kakao.maps.LatLng(lat_lng.lat, lat_lng.lng);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      this.roadviewClient.getNearestPanoId(position, 50, (panoId) => {
        this.roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
};
</script>

<style>
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 1000px;
}
#roadview_card {
  position: absolute;
  top: 140px;
  left: 560px;
  bottom: 0;
  margin: 10px 0 30px 10px;
  padding: 10px;
  background: rgb(255, 255, 255);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
#close_btn {
  position: absolute;
  right: 10px;
  top: -30px;
}
#roadview_main {
  width: 100%;
  height: 100%;
}
</style>

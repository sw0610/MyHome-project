import Vue from "vue";

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUser, faBookmark,faArrowRightFromBracket, faUserGear, faLock, faTrashCan, faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import { faQuoteLeft, faQuoteRight} from '@fortawesome/free-solid-svg-icons'
import { faCircleCheck } from '@fortawesome/free-regular-svg-icons'
library.add(faUser,faBookmark,faArrowRightFromBracket, faUserGear, faLock, faCircleCheck, faTrashCan, faMagnifyingGlass,
            faQuoteLeft, faQuoteRight), 

Vue.component('font-awesome-icon', FontAwesomeIcon)
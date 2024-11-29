import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser, faExchangeAlt, faCar, faHome, faList, faTags, faCog } from '@fortawesome/free-solid-svg-icons';

library.add(faUser, faExchangeAlt, faCar, faHome, faList, faTags, faCog);

const app = createApp(App);

app.use(router).component('font-awesome-icon', FontAwesomeIcon).mount('#app');

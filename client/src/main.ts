import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import axios from 'axios';
import VueTheMask from 'vue-the-mask';

const app = createApp(App);
app.use(router);
app.use(VueTheMask);
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
app.mount('#app');

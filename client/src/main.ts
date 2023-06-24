import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import axios from 'axios';

createApp(App).use(router).mount('#app')

const app = createApp(App);
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
app.use(router);
app.mount('#app');

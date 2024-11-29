import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

// Importações do Font Awesome
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser, faExchangeAlt, faCar, faHome, faList, faTags, faCog } from '@fortawesome/free-solid-svg-icons';

// Adicione os ícones à biblioteca
library.add(faUser, faExchangeAlt, faCar, faHome, faList, faTags, faCog);

// Criação do app Vue
const app = createApp(App);

// Use o router e registre o componente Font Awesome globalmente
app.use(router).component('font-awesome-icon', FontAwesomeIcon).mount('#app');

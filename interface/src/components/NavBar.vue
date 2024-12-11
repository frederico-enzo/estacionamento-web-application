<template>
  <div id="app">
    <div class="sidebar">
      <ul>
        <li :class="{ 'active-item': isActive('/') }" @click="setActive('/')"><router-link to="/"><i
              class="fas fa-home"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/condutor') }" @click="setActive('/condutor')"><router-link
            to="/condutor"><i class="fas fa-user"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/veiculo') }" @click="setActive('/veiculo')"><router-link
            to="/veiculo"><i class="fas fa-car"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/movimentacoes') }" @click="setActive('/movimentacoes')"><router-link
            to="/movimentacoes"><i class="fas fa-exchange-alt"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/modelo') }" @click="setActive('/modelo')"><router-link to="/modelo"><i
              class="fas fa-list"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/marca') }" @click="setActive('/marca')"><router-link to="/marca"><i
              class="fas fa-tags"></i></router-link></li>
        <li :class="{ 'active-item': isActive('/configs') }" @click="setActive('/configs')"><router-link to="/configs"><i
              class="fas fa-cog"></i></router-link></li>
      </ul>
    </div>
    <div class="content ">
      <div class="content-inner">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

export default defineComponent({
  name: 'NavbarComponent',

  setup() {
    const route = useRoute();
    const activeLink = ref<string>(route.path);

    const setActive = (link: string) => {
      activeLink.value = link;
    };

    const isActive = (link: string) => activeLink.value === link;

    onMounted(() => {
      activeLink.value = route.path;
    });

    return { setActive, isActive };
  },
});
</script>

<style scoped>
body,
html {
  height: 100%;
  margin: 0;
  font-family: Arial, sans-serif;
}

.sidebar {
  height: calc(100vh - 20px);
  width: 80px;
  position: fixed;
  top: 10px;
  left: 10px;
  background-color: #3b82f6;
  padding-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 15px;
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.sidebar li {
  width: 60px;
  text-align: center;
  margin-bottom: 20px;
}

.sidebar li a {
  display: block;
  padding: 10px;
  font-size: 25px;
  color: white;
  text-decoration: none;
  border-radius: 15px;
  transition: background-color 0.3s, color 0.3s, padding 0.3s;
}

.sidebar li a:hover {
  background-color: white;
  color: black;
  padding: 10px;
}

.sidebar li.active-item a {
  background-color: white;
  color: black;
}

.content {
  height: calc(100vh - 20px);
  margin-left: 110px;
  margin-right: 20px;
  margin-top: 10px;
  padding: 20px;
  background-color: #b4b4b482;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.7);  /* Horizontal, vertical, desfoque e cor */

}

.content-inner {
  overflow-y: auto;
  height: 100%;
}

.sidebar .spacer {
  flex-grow: 1;
}
</style>
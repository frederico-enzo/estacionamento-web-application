<template>
  <div class="d-flex">
    <div class="sidebar shadow vh-100 p-3">
      <ul class="nav flex-column">
        <li class="nav-item mb-2" :class="{'active-item': isActive('/')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/" @click="setActive('/')">
            <font-awesome-icon icon="home" class="me-2" />
            <span>Início</span>
          </router-link>
        </li>
        <li class="nav-item mb-2" :class="{'active-item': isActive('/condutores')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/condutores" @click="setActive('/condutores')">
            <font-awesome-icon icon="user" class="me-2" />
            <span>Condutor</span>
          </router-link>
        </li>
        <li class="nav-item mb-2" :class="{'active-item': isActive('/veiculos')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/veiculos" @click="setActive('/veiculos')">
            <font-awesome-icon icon="car" class="me-2" />
            <span>Veículos</span>
          </router-link>
        </li>
        <li class="nav-item mb-2" :class="{'active-item': isActive('/movimentacoes')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/movimentacoes" @click="setActive('/movimentacoes')">
            <font-awesome-icon icon="exchange-alt" class="me-2" />
            <span>Movimentações</span>
          </router-link>
        </li>
        <li class="nav-item mb-2" :class="{'active-item': isActive('/modelos')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/modelos" @click="setActive('/modelos')">
            <font-awesome-icon icon="list" class="me-2" />
            <span>Modelos</span>
          </router-link>
        </li>
        <li class="nav-item mb-2" :class="{'active-item': isActive('/marcas')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/marcas" @click="setActive('/marcas')">
            <font-awesome-icon icon="tags" class="me-2" />
            <span>Marcas</span>
          </router-link>
        </li>
        <li class="nav-item" :class="{'active-item': isActive('/configs')}">
          <router-link class="nav-link d-flex align-items-center sidebar-link" to="/configs" @click="setActive('/configs')">
            <font-awesome-icon icon="cog" class="me-2" />
            <span>Configuração</span>
          </router-link>
        </li>
      </ul>
    </div>
    <div class="content flex-grow-1 p-3">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'NavbarComponent',
  setup() {
    const activeLink = ref<string>('/'); // Link ativo inicial

    const setActive = (link: string) => {
      activeLink.value = link;
    };
    const isActive = (link: string) => activeLink.value === link;
    return { setActive, isActive };
  },
});
</script>

<style scoped>
.sidebar {
  width: 280px;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.8); 
  backdrop-filter: blur(10px); 
  border-right: 1px solid rgba(0, 0, 0, 0.1); 
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
}

.sidebar .nav-link {
  color: #000; 
  font-size: 1rem;
  padding: 10px 15px;
  border-radius: 6px;
  transition: all 0.3s ease-in-out;
}

.sidebar .nav-link:hover {
  background: rgba(0, 120, 212, 0.2); 
  transform: translateX(5px); 
  color: #0057d8; 
}

.sidebar .nav-link.active {
  background: rgba(0, 120, 212, 0.5); 
  font-weight: bold;
  color: white;
}

.active-item .nav-link {
  background: rgba(0, 120, 212, 0.865); 
  color: white;
  font-weight: bold;
}

.nav-item {
  text-align: left;
  margin-bottom: 0.5rem;
}

/* Ajustes para o conteúdo principal */
.content {
  margin-left: 280px;
  padding: 20px;
  background-color: #f3f4f6; /* Fundo claro para o conteúdo */
  min-height: 100vh;
}
</style>

<template>
  <div class="home">
<NavBar/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import NavBar from "../components/NavBar.vue"


export default defineComponent({
  name: "HomeView",
  components: {
    NavBar,

  },
  data() {
    return {
      selectedOption: 'condutores',
    };
  },
  methods: {
    navigateToTable() {
      this.$router.push(`/${this.selectedOption}`);
    },
  },
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&family=Poppins:wght@500;800&display=swap");

.home {
  height: 100vh;
  background: linear-gradient(311.76deg, #d4e7fe -15.24%, #ffffff 78.85%);
}


.lestGo {
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>

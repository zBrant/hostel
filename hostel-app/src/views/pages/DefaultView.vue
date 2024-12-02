<template>
  <div>
    <default-header class="header-default"></default-header>
    <p>teste novo</p>
    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script>
import DefaultHeader from "@/components/DefaultHeader.vue";
import DefaultFooter from "@/components/DefaultFooter.vue";

export default {
  name: 'HomeView',
  components: {DefaultFooter, DefaultHeader}
}
</script>

<style scoped>
.header-default {
  min-height: 10vh;
}

.footer-default {
  min-height: 5vh;
}

.body-height {
  min-height: 85vh;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

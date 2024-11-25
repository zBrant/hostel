<template>
  <div>
    <default-header class="header-default"></default-header>
    <router-view class="body-height"/>
    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script setup>
import DefaultHeader from '@/components/DefaultHeader.vue'
import DefaultFooter from '@/components/DefaultFooter.vue'
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

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
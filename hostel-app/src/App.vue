<template>
  <div>
    <default-header></default-header>
    <router-view/>
    <default-footer></default-footer>
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
  margin: 0 10% 0;
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
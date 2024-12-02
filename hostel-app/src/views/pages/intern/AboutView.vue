<template>
  <div>
    <default-header class="header-default"></default-header>
    <div class="about">
      <h1>This is an about page</h1>
    </div>
    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script>
import DefaultHeader from "@/components/DefaultHeader.vue";
import DefaultFooter from "@/components/DefaultFooter.vue";

export default {
  name: 'about-view',
  components: {DefaultFooter, DefaultHeader}
}

</script>
<template>
  <div class="diagonal-background align-content-center">
    <p class="text-white">© 2024 Hostelpoint.com Limited</p>
  </div>
</template>

<script>
export default {
  name: 'default-footer',
  props: {
    msg: String
  }
}
</script>

<style scoped>
.diagonal-background {
  position: relative;
  background-color: #121417;
}

.diagonal-background::before {
  content: '';
  position: absolute;
  top: -50px;
  left: 0;
  width: 100%;
  height: calc(100% + 50px);
  background-color: #121417;
  clip-path: polygon(0 50px, 100% 0, 100% 100%, 0 100%);
  z-index: -1;
}
</style>
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

  left: 0;
  width: 100%;
}

.text-white {
  position: relative;
}
</style>
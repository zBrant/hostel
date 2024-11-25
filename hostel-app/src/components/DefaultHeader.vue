<template>
  <div class="default-page pl-3 pr-3">
      <router-link to="/">
        <img alt="Vue logo" style="width: 200px" src="../assets/logo.png">
      </router-link>

    <div class="padding-items">
        <router-link to="/about">
          <v-icon icon="mdi-account-outline" size="30" class="user-icon"/>
        </router-link>

      <v-menu
          transition="slide-y-transition"
      >
        <template v-slot:activator="{ props }">
            <v-icon v-bind="props" icon="mdi-menu" size="30" class="user-icon"/>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item-title> Anunciar </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

    </div>
  </div>
</template>

<script>
export default {
  name: 'default-header',
  props: {
    msg: String
  }
}
</script>

<style scoped>
.default-page {
  border-radius: 25px;
  height: 70px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 0 15% 0;
}

.user-icon {
  color: black !important;
}

.user-icon:hover {
  color: #5a5a5a !important;
}

.padding-items > *{
 padding-right: 10px;
}


</style>
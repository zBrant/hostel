<template>
  <div class="default-page pl-3 pr-3">
      <router-link to="/">
        <img alt="Vue logo" style="width: 200px" src="../assets/logo.png">
      </router-link>

    <div class="padding-items">
        <router-link to="/about">
          <v-icon icon="mdi-account-circle" size="30" class="user-icon"/>
        </router-link>

      <v-menu
          transition="slide-y-transition"
      >
        <template v-slot:activator="{ props }">
            <v-icon
                v-bind="props"
                icon="mdi-menu" size="30" class="user-icon"/>
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
  margin-top: 10px;
  border-radius: 25px;
  height: 70px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.user-icon {
  color: black !important;
}

.padding-items > *{
 padding-right: 10px;
}


</style>
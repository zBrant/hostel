<template>
  <div class="default-page pl-3 pr-3">
      <router-link to="/home">
        <img alt="Vue logo" style="width: 200px" src="../assets/logo.png">
      </router-link>

    <div class="padding-items d-flex text-black">


      <router-link to="/register-room" class="text-decoration-none">
        <div class="mr-10 border-registrer-property align-self-center pt-1 pb-1" v-if="logedUser">
            <v-icon icon="mdi-office-building mr-1" size="20" />
            <span>Resgistre sua propriedade</span>
        </div>
      </router-link>

      <router-link to="/sign-up">
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
            <v-list-item-title>
              <a href="https://google.com" style="color: black; text-decoration: none" target="_blank">
                <v-icon icon="mdi-help-circle" size="20" class="user-icon mr-1"/>
                Ajuda
              </a>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

    </div>
  </div>
</template>

<script>
export default {
  name: 'default-header',
  props: {},
  data(){
    return {
      logedUser: false,
    }
  },
  mounted() {
    if (this.$store.state.userInfo) this.logedUser = true
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

.user-icon:hover{
  color: #5a5a5a !important;
}

.border-registrer-property:hover {
  background-color: #e1e1e1 !important;
  cursor: pointer;
}

.padding-items > *{
 padding-right: 10px;
}
.text-decoration-none {
  color: black;
  text-decoration: none;
}

.border-registrer-property {
  border: 2px solid grey ;
  border-radius: 30px;
  padding: 0 20px;
}

.text-black {
  font-size: .75rem;
  font-weight: 800;
  letter-spacing: .0125rem;
  line-height: 1rem;
}
</style>
<template>
  <div>
    <default-header class="header-default"></default-header>
    <div class="menu ml-auto mr-auto d-flex flex-column align-center justify-space-between body-div">
        <div class="bg-color form-div">
          <div class="titulo">
            <h1>Registrar quarto</h1>
          </div>
          <v-form v-model="valid">
            <v-container>
              <v-row>
                <v-col cols="12" md="12">
                  <v-text-field
                      prepend-inner-icon="mdi-map-marker"
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address"
                      :rules="rules"
                      placeholder="Endereço"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="12">
                  <v-text-field
                      prepend-inner-icon="mdi-text"
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      :rules="rules"
                      v-model="description"
                      placeholder="Descrição"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                      prepend-inner-icon="mdi-bed"
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="bedCount"
                      :rules="rules"
                      placeholder="Número de camas"
                      validate-on="lazy blur"
                      type="number"
                      required
                  ></v-text-field>

                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                      prepend-inner-icon="mdi-currency-usd"
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="price"
                      :rules="rules"
                      placeholder="Preço"
                      validate-on="lazy blur"
                      type="number"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="12">
                  <v-file-input
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      accept="image/*"
                      label="selecionar foto do quarto"
                  ></v-file-input>
                </v-col>
              </v-row>
              <v-btn class="mt-5" @click="doLogin" type="submit" elevation="4" block height="45" color="#6e54b5">Registrar</v-btn>
            </v-container>
          </v-form>
        </div>

      <v-alert
          v-if="showAlert"
          class="alert"
          density="compact"
          closable
          :text="errorMessage"
          color="error"
          icon="$info"
          @click:close="showAlert = false"
      ></v-alert>
    </div>
    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script>
import DefaultHeader from "@/components/DefaultHeader.vue";
import DefaultFooter from "@/components/DefaultFooter.vue";
import AuthenticationService from "@/services/AuthenticationService";
import router from "@/router";

export default {
  name: 'register-room-view',
  components: { DefaultFooter, DefaultHeader },
  props: {
    msg: String
  },
  data(){
    return {
      visible: false,
      rules: [ value => { return !!value || 'Campo Obrigatório.' }],
      valid: false,
      errorMessage: '',
      showAlert: false,

      description: '',
      price: '',
      bedCount: '',
      rented: '',
      userId: '',
      address: '',
    }
  },
  methods: {
    doLogin(){
      if (!this.valid)  return

      AuthenticationService.login(this.mountPayload())
          .then(response => {
            this.$store.state.userInfo = response.data
            router.push({ path: '/home' })
          })
          .catch(e => {
            this.errorMessage = e.response.data.message
            this.showAlert = true

            setTimeout(() => {
              this.showAlert = false
            }, 3000)
          });
    },
    mountPayload(){
      return {
        email: this.email,
        password: this.password,
      }
    }
  }
}

</script>

<style scoped>
.bg-color {
  background-color: #2b2638;
}

.header-default {
  height: 10vh;
}

.footer-default {
  height: 5vh;
}

.body-div {
  height: 85vh;
}

.menu {
  background-color: white;
}

.form-div {
  color: white;
  border-radius: 20px;
  width: 600px;
  align-content: center;
  padding: 20px;
  box-shadow: 5px 5px 25px 15px rgba(0, 0, 0, 0.4);
  margin: auto 0;
}

.text-field-bg text-area {
  background-color: #3c364c;
}

.titulo h1 {
  font-size: -webkit-xxx-large;
  padding-left: 16px;
  text-align: left;
}

.titulo p{
  color: #8c8b94;
  padding-left: 16px;
  text-align: left;
}

.titulo span{
  color: #8066c6;
  text-decoration: underline;
}

.alert {
  position: absolute;
  right: 30px;
  top: 30px;
}

</style>
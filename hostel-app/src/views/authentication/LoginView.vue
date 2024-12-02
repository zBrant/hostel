<template>
  <div class="menu ml-auto mr-auto d-flex  flex-column align-center justify-space-between">
    <div class="menu-size width-default d-flex mt-auto mb-auto">

      <div class="bg-color height-default img-div"></div>

      <div class="bg-color flex-grow-1 form-div">
        <div class="titulo">
          <h1>Entrar</h1>
          <p>Não possui uma conta? <router-link to="/sign-up"><span>Registre-se</span></router-link></p>
        </div>
        <v-form v-model="valid">
          <v-container>
            <v-row>
              <v-col cols="12" md="12">
                <v-text-field
                    prepend-inner-icon="mdi-email-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    :rules="[...rules, ...emailRules]"
                    placeholder="E-mail"
                    type="email"
                    required
                    validate-on="lazy blur"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" md="12">
                <v-text-field
                    prepend-inner-icon="mdi-lock-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    v-model="password"
                    placeholder="Senha"
                    :rules="rules"
                    validate-on="lazy blur"
                    :type="visible ? 'text' : 'password'"
                    required
                    :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
                    @click:append-inner="visible = !visible"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-btn class="mt-5" @click="doLogin" type="submit" elevation="4" block height="45" color="#6e54b5">Entrar</v-btn>
          </v-container>
        </v-form>
      </div>
    </div>
    <default-footer class="w-100 text-sm-h6" style="max-height: 40px; min-height: 40px"></default-footer>
  </div>
</template>

<script>
import DefaultFooter from "@/components/DefaultFooter.vue";
import AuthenticationService from "@/services/AuthenticationService";

export default {
  name: 'login-view',
  components: {DefaultFooter},
  props: {
    msg: String
  },
  data(){
    return {
      visible: false,
      emailRules: [ v => /.+@.+\..+/.test(v) || 'Endereço de email inválido' ],
      rules: [ value => { return !!value || 'Campo Obrigatório.' }],
      valid: false,
      email: null,
      password: null,
    }
  },
  methods: {
    doLogin(){
      if (!this.valid)  return

      AuthenticationService.login(this.mountPayload)
          .then(response => {
            console.log('response sign-up', response);
          })
          .catch(e => {
            console.log(e);
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

.height-default {
  height: 100% !important;
}

.menu {
  background-color: white;
  height: 100vh;
}

.menu-size {
  margin: 0 auto;
  height: 600px;
  width: 1200px;
  border-radius: 55px;
  box-shadow: 5px 5px 25px 15px rgba(0, 0, 0, 0.4);
}

.img-div {
  background-image: url("../../assets/login-image3-small.jpg");
  background-size: cover;
  width: 600px;
  height: 100%;
  border-radius: 35px 0 0 35px;
}

.form-div {
  color: white;
  border-radius: 0 20px 20px 0;
  width: 50%;
  align-content: center;
  padding: 20px;
}

.text-field-bg text-area {
  background-color: #3c364c;
}

.titulo h1{
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

</style>
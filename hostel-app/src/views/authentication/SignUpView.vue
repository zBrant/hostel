<template>
  <div class="menu ml-auto mr-auto d-flex  flex-column align-center justify-space-between">
    <div class="menu-size width-default d-flex mt-auto mb-auto">

      <div class="bg-color height-default img-div"></div>

      <div class="bg-color flex-grow-1 form-div">
        <div class="titulo">
          <h1>Criar uma conta</h1>
          <p>Já possui uma conta?
            <router-link to="/login"><span>Entrar</span></router-link>
          </p>
        </div>
        <v-form v-model="valid" ref="form">
          <v-container>
            <v-row>
              <v-col cols="12" md="6">
                <v-text-field
                    prepend-inner-icon="mdi-account-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    v-model="name"
                    placeholder="Nome"
                    :rules="rules"
                    required
                    validate-on="lazy blur"
                ></v-text-field>

              </v-col>

              <v-col cols="12" md="6">
                <v-text-field
                    prepend-inner-icon="mdi-phone-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    v-model="phoneNumber"
                    placeholder="Telefone"
                    required
                ></v-text-field>

              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" md="6">
                <v-text-field
                    prepend-inner-icon="mdi-card-account-details-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    v-model="cpfCnpj"
                    placeholder="CPF ou CNPJ"
                    required
                ></v-text-field>
              </v-col>

              <v-col cols="12" md="6">
                <v-text-field
                    prepend-inner-icon="mdi-email-outline"
                    base-color="transparent"
                    color="#6d618e"
                    bg-color="#3c364c"
                    variant="outlined"
                    v-model="email"
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
                    :rules="rules"
                    placeholder="Nova Senha"
                    type="text"
                    required
                    validate-on="lazy blur"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-btn class="mt-5" @click="sendData" type="submit" elevation="4" block height="45" color="#6e54b5">Criar conta</v-btn>
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
  name: 'signup-view',
  components: {DefaultFooter},
  props: {
    msg: String
  },
  data() {
    return {
      valid: false,
      checkbox: false,
      name: null,
      phoneNumber: null,
      cpfCnpj: null,
      email: null,
      password: null,
      rules: [value => { return !!value || 'Campo Obrigatório.' }],
      emailRules: [v => /.+@.+\..+/.test(v) || 'Endereço de email inválido'],
    }
  },
  methods: {
    async sendData() {
      if (!this.valid)  return

      AuthenticationService.signup(this.mountPayload)
          .then(response => {
            console.log('response sign-up', response);
          })
          .catch(e => {
            console.log(e);
          });
    },
    mountPayload(){
      return {
        name: this.name,
        phoneNumber: this.phoneNumber,
        cpfCnpj: this.cpfCnpj,
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
  background-color: #fafafa;
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

.titulo h1 {
  font-size: -webkit-xxx-large;
  padding-left: 16px;
  text-align: left;
}

.titulo p {
  color: #8c8b94;
  padding-left: 16px;
  text-align: left;
}

.titulo span {
  color: #8066c6;
  text-decoration: underline;
}

</style>
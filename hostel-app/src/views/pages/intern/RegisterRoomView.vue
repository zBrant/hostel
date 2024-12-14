<template>
  <div>
    <default-header class="header-default"></default-header>
    <div class="menu ml-auto mr-auto d-flex flex-column align-center justify-space-between body-div">
        <div class="bg-color form-div">
          <div class="titulo">
            <h1>Registrar quarto</h1>
          </div>
          <v-form v-model="valid" ref="form">
            <v-container>
              <v-row>
                <v-col cols="12" md="4">
                  <v-text-field
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address.cep"
                      :rules="rules"
                      placeholder="CEP"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="4">
                  <v-text-field
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address.state"
                      :rules="rules"
                      placeholder="Estado"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="4">
                  <v-text-field
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address.number"
                      :rules="rules"
                      placeholder="Número"
                      type="number"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address.country"
                      :rules="rules"
                      placeholder="País"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                      base-color="transparent"
                      color="#6d618e"
                      bg-color="#3c364c"
                      variant="outlined"
                      v-model="address.city"
                      :rules="rules"
                      placeholder="Cidade"
                      type="text"
                      required
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="12" md="12">
                  <v-text-field
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
                      v-model="photo"
                      label="Foto do quarto"
                  ></v-file-input>
                </v-col>
              </v-row>
              <v-btn class="mt-5" @click="sendRoom" type="submit" elevation="4" block height="45" color="#6e54b5">Registrar</v-btn>
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
import RoomService from "@/services/RoomService";
import { ref } from "vue";
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
      photo: ref(null),
      price: '',
      bedCount: '',
      address: {
        cep: null,
        city: null,
        country: null,
        number: null,
        state: null,
      },
    }
  },
  methods: {
    async sendRoom(){
      if (!this.valid)  return

      await this.sendInfo()
    },
    async sendInfo(){
      RoomService.sendRoom(this.mountPayloadInfo())
          .then(response => this.sendPhoto(response.data.id))
          .catch(e => this.alertHandler(e))
    },
    async sendPhoto(roomId){
      RoomService.sendPhoto(this.mountPayloadPhoto(roomId))
          .then(() => router.push({ path: '/home' }))
          .catch(e => this.alertHandler(e))
    },
    alertHandler(e){
      this.errorMessage = e.response.data.message
      this.showAlert = true

      setTimeout(() => {
        this.showAlert = false
      }, 1000)
    },
    mountPayloadInfo(){
      return {
        description: this.description,
        price: this.price,
        bedCount: this.bedCount,
        rented: false,
        address: this.address,
        userId: this.$store.state.userInfo.id
      }
    },
    mountPayloadPhoto(roomId){
      return {
        photo: this.photo,
        userId: roomId,
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
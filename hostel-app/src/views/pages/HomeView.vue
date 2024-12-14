<template>
  <div>
    <default-header class="header-default"></default-header>
    <div class="body">
      <div class="hero position-relative">

        <div class="hero-title d-flex justify-space-between align-center">
          <div class="flex-grow-1">
            <h1 class="hero-title-h1">Conheça novas pessoas.</h1>
            <h3 class="hero-title-h3">Escolha onde queres ir e arranje novas companhias.</h3>
          </div>
          <div class="hero-image"> </div>
        </div>
      </div>
      <div class="hero-search">
        <v-text-field
            prepend-inner-icon="mdi-map-marker"
            placeholder="Onde você quer ir?"
            :rounded="true"
            variant="solo"
            v-model="filter"
            append-inner-icon="mdi-search-web"
            @click:append-inner="searchWithFilter"
        ></v-text-field>
      </div>

      <div class="rooms-div">
        <p class="rooms-div-text ml-2">Descubra ofertas imperdíveis nos melhores hostels do mundo.</p>

        <ul class="rooms-cards d-flex flex-wrap" >
          <template v-for="(room, index) in paginatedRooms" :key="index">
            <li class="rooms-card ma-2" @click="selectRoom(room)">
              <div class="room-image">
                <v-img
                    height="200"
                    width="200"
                    cover
                    aspect-ratio="16/9"

                    :src="getImage(room.id)"
                ></v-img>
              </div>

              <div class="room-description">
                <h3>{{ room.description }}</h3>
                <p>{{ room.address.city }}</p>
                <p class="room-price mt-1">R$ {{ room.price }}</p>
              </div>
            </li>
          </template>
        </ul>

        <div class="empty-list d-flex flex-column" v-if="rooms.length <= 0">
          <v-icon aria-hidden="false" size="100px">
            mdi-cup-outline
          </v-icon>
          <p>Listagem vazia</p>
        </div>

        <v-pagination
            v-else
            v-model="currentPage"
            :length="totalPages"
            :total-visible="5"
            class="mt-4"
        ></v-pagination>
      </div>
    </div>

    <div class="div-newsletter bg-cyan-darken-1 d-flex align-center justify-center">
      <div class="div-newsletter-texts">
        <h3 class="text-left div-newsletter-texts-title">Junta-te à nossa newsletter aventureira!</h3>
        <p class="text-left">Assina a nossa newsletter para ofertas, inpsirações e outras promoções de viagens!</p>
      </div>
      <div class="div-newsletter-email">
        <v-text-field
            prepend-inner-icon="mdi-email"
            append-inner-icon="mdi-send-variant"
            placeholder="O teu endereço de email"
            :rounded="true"
            variant="solo"
            v-model="emailNewsletter"
            type="email"
            @click:append-inner="sendEmailToSubscribe"
        ></v-text-field>
      </div>
    </div>

    <v-alert
        v-if="showEmailSended"
        class="alert"
        density="compact"
        closable
        :text="messageEmailSended"
        type="success"
        @click:close="showEmailSended = false"
    ></v-alert>

    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script>
import DefaultHeader from "@/components/DefaultHeader.vue";
import DefaultFooter from "@/components/DefaultFooter.vue";
import RoomService from "@/services/RoomService";

export default {
  name: 'HomeView',
  components: {DefaultFooter, DefaultHeader},
  data(){
    return {
      emailNewsletter: '',
      messageEmailSended: 'email inscrito com sucesso.',
      showEmailSended: false,
      rooms: [],
      imageUrls: [],
      currentPage: 1,
      itemsPerPage: 4,
      filter: '',
    }
  },
  computed: {
    paginatedRooms() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = this.currentPage * this.itemsPerPage;
      return this.rooms.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.rooms.length / this.itemsPerPage);
    }
  },
  async mounted() {
    await this.getAllRooms()
  },
  methods: {
    async searchWithFilter(){
      await this.getAllRooms({ filter: this.filter })
    },
    async getAllRooms(){
      RoomService.getAll(this.mountPayloadSearch())
          .then(response => this.addRoomsToList(response.data._embedded.roomModelList))
          .catch(e => this.alertHandler(e))
    },
    addRoomsToList(data){
      this.rooms = data
    },
    async getImage(roomId) {
      const image = await RoomService.getImage(roomId)
          .then(response => response)
          .catch(e => this.alertHandler(e))

      return image
    },
    mountPayloadSearch(){
      return {
        filter: this.filter,
        currentPage: this.currentPage,
        itemsPerPage: this.itemsPerPage,
      }
    },
    selectRoom(room){
      console.log('selecionu quarto', room)
    },
    sendEmailToSubscribe() {
      this.emailNewsletter = ''
      this.showEmailSended = true

      setTimeout(() => this.showEmailSended = false , 3000)

    },
    alertHandler(e){
      if (!e.data) return
      this.errorMessage = e.response.data.message
      this.showAlert = true

      setTimeout(() => {
        this.showAlert = false
      }, 2000)
    },
  }
}
</script>

<style scoped>
.header-default {
  height: 10vh;
}

.footer-default {
  height: 5vh;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.empty-list {
  justify-content: center !important;
  align-content: center !important;
  flex-wrap: wrap;
}

.body {
  min-height: 85vh;
}

.rooms-div {
  margin: 30px 15%;
  display: flex;
  flex-direction: column;
  padding: 20px;
  background-color: #061738;
  color: white;
  border-radius: 40px;
  box-sizing: border-box;
}

.rooms-div-text {
  text-align: left;
  font-size: 1.2rem;
  margin-bottom: 20px;
}

.room-price {
  color: rgb(18, 20, 23);
  font-family: Inter, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 1.125rem;
  font-weight: 800;
  letter-spacing: -0.00625rem;
  line-height: 1.5rem;
  order: 1;
}

.alert {
  position: fixed;
  right: 30px;
  top: 30px;
}

.room-description{
  color: black;
  padding: 15px 10px;
}

.div-newsletter {
  max-height: 140px ;
}

.div-newsletter-texts {
  padding: 60px;
  color: #121b35;
}

.div-newsletter-texts-title {
  font-size: 1.8rem;
}

.div-newsletter-email {
  width: 760px;
}

.hero-search {
  margin: -35px 22% 0;
}

.room-image {
  background-image: url("../../assets/rooms/room1.png");
  background-size: cover;
  width: auto;
  height: 200px;
  border-radius: 20px;
}

.rooms-card {
  background-color: white;
  padding: 10px;
  border-radius: 30px;
  list-style-type: none;
  cursor: pointer;
  width: 307px;
}

.hero {
  margin: 0 15%;
  background-color: #59348b;
  color: white;
  padding: 56px 64px;
  border-radius: 40px;
  box-shadow: 5px 5px 45px 15px rgba(89, 52, 180, .4);
}

.hero-image {
  background-image: url("../../assets/heroimage.png");
  background-size: auto;
  height: 300px;
  width: 440px;
}

.hero-title-h1 {
  text-align: left;
  font-size: 4rem;
  font-weight: 800;
  letter-spacing: -.025rem;
  line-height: 5rem;
}

.hero-title-h3 {
  font-size: 1.5rem;
  font-weight: 800;
  letter-spacing: -.0125rem;
  line-height: 2rem;
  max-width: none;
  text-align: left;
}

@media screen and (max-width: 1730px) {
  .hero-image {
    display: none;
  }
}
</style>

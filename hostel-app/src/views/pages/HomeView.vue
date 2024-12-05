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
        ></v-text-field>
      </div>

      <div class="rooms-div">
        <p class="rooms-div-text ml-2">Descubra ofertas imperdíveis nos melhores hostels do mundo.</p>

        <ul class="rooms-cards d-flex flex-wrap ">
          <template v-for="(room, index) in paginatedRooms" :key="index">
            <li class="rooms-card ma-2" @click="selectRoom(room)" >
              <div class="room-image"></div>
              <div class="room-description">
                <h3>{{ room.title }}</h3>
                <p>{{ room.location}}</p>
              </div>
            </li>
          </template>
        </ul>

        <v-pagination
            v-model="currentPage"
            :length="totalPages"
            :total-visible="5"
            class="mt-4"
        ></v-pagination>
      </div>
    </div>
    <default-footer class="footer-default"></default-footer>
  </div>
</template>

<script>
import DefaultHeader from "@/components/DefaultHeader.vue";
import DefaultFooter from "@/components/DefaultFooter.vue";

export default {
  name: 'HomeView',
  components: {DefaultFooter, DefaultHeader},
  data(){
    return {
      rooms: [
        { title: 'Quarto com vista para o mar', location: 'Sevilha, Espanha' },
        { title: 'Quarto no centro da cidade', location: 'Lisboa, Portugal' },
        { title: 'Suíte aconchegante', location: 'Paris, França' },
        { title: 'Quarto com varanda', location: 'Rio de Janeiro, Brasil' },
        { title: 'Loft moderno', location: 'Berlim, Alemanha' },
        { title: 'Apartamento espaçoso', location: 'Barcelona, Espanha' },
        { title: 'Estúdio compacto', location: 'Tóquio, Japão' },
        { title: 'Chalé nas montanhas', location: 'Genebra, Suíça' },
        { title: 'Quarto com vista para o lago', location: 'Toronto, Canadá' },
        { title: 'Cobertura de luxo', location: 'Nova York, EUA' }
      ],
      currentPage: 1,
      itemsPerPage: 4
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
  methods: {
    selectRoom(room){
      console.log('selecionu quarto', room)
    }
  }
}
</script>

<style scoped>
.header-default {
  min-height: 10vh;
}

.footer-default {
  min-height: 5vh;
}

.body-height {
  min-height: 85vh;
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

.room-description{
  color: black;
  padding: 15px 10px;
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

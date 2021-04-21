<template>
  <div>
    <h1 class="mb-10">Nos articles les plus populaires</h1>
    <v-col>
      <v-row v-if="items === null || items.length !== 0" class="d-flex justify-center">
        <div v-for="item in items" :key="item.id" class="col-3 mx-4 mb-5 pa-0">
     
          <div
            :style="{ cursor: 'pointer' }"
            @click="$router.push(`article/${item.id}`)"
          >
            <v-img :src="item.pictures[0]" contain />
            <v-card-title class="mb-0">{{ item.name }}</v-card-title>
            <v-card-text
              class="d-flex align-center pb-0"
              :style="{ height: '50px' }"
            >
              <p class="mb-0" :style="{ textAlign: 'left' }">
                {{ item.description }}
              </p></v-card-text
            >
            <h4 class="text-left pl-4 font-weight-black">{{ item.price }} €</h4>

            <v-divider class="mx-4 mt-5 d-flex align-center"></v-divider>
          </div>
        </div>
      </v-row>
      <v-row v-else>Loading ...</v-row>
    </v-col>
  </div>
</template>

<script>
export default {
  name: 'PopularArticles',
  props: {
    items: {
      type: Array
    }
  },
};
</script>

<style lang="scss" scoped></style>

<template>
  <v-footer class="footer d-flex justify-center" app :padless="padless">
    <v-card flat tile width="100%" class="grey lighten-4 text-center py-3">
      <ul class="navigation-list">
        <li
          v-for="item in navigationFooter"
          :key="item.path"
          class="navigation-list-items"
        >
          <router-link
            :to="item.path"
            :active="isActive"
            class="navigation-list-items-link"
            >{{ item.name }}</router-link
          >
        </li>
      </ul>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  components: {},
  data() {
    return {
      isActive: true,
      padless: true,
      navigationFooter: [
        {
          name: 'A propos',
          path: '/aboutus'
        },
        {
          name: 'Nous contacter',
          path: '/contact'
        },
        {
          name: 'Copyright',
          path: '/copyright'
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
.footer {
  .navigation-list {
    display: flex;
    justify-content: center;
    list-style: none;
    padding: 15px;
    .navigation-list-items {
      font-size: 20px;
      .navigation-list-items-link {
        text-decoration: none;
        margin-right: 15px;
        color: black;
        padding-bottom: 3px;
      }
      .navigation-list-items-link:hover {
        border-bottom: 2px solid black;
      }
      .router-link-active {
        border-bottom: 2px solid black;
      }
    }
  }
}
</style>

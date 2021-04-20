<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
  </div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
import Breadcrumb from '../Breadcrumb.vue';
export default {
  name: 'CartesPaiement',
  components: { ProfilNavigation, Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Profil',
          disabled: true
        },
        {
          text: 'Cartes de paiement'
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped></style>

<template>
  <div class="navigation">
    <ProfilNavigation />
    <Breadcrumb :items="links" />
  </div>
</template>

<script>
import ProfilNavigation from '../../components/Profil/ProfilNavigation.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
export default {
  name: 'CartesPaiement',
  components: { ProfilNavigation, Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/home'
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

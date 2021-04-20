<template>
  <div>
    <Breadcrumb :items="links" />
    <h1>A propos</h1>
  </div>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';
export default {
  name: 'Aboutus',
  components: { Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'A propos',
          disabled: true
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped></style>

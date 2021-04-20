<template>
  <div>
    <Breadcrumb :items="links" />
    <h1>Copyright</h1>
  </div>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';
export default {
  name: 'Copyright',
  components: { Breadcrumb },
  data() {
    return {
      links: [
        {
          text: 'Home',
          to: '/'
        },
        {
          text: 'Copyright',
          disabled: true
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped></style>

<template>
  <div>
    <v-breadcrumbs :items="items" divider="/"></v-breadcrumbs>
  </div>
</template>

<script>
export default {
  name: 'Breadcrumbs',
  props: {
    items: {
      type: Array
    }
  }
};
</script>

<style lang="scss" scoped></style>

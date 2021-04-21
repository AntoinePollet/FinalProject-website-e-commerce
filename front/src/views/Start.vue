<template>
  <div class="header">
    <div class="center d-flex justify-center align-center flex-column">
      <h1 class="title white--text font-weight-bold mb-10">KYOKYU</h1>
      <router-link to="/home" class="link">Accéder au site</router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Open+Sans:400,300');
.header {
  width: 100%;
  height: 100%;
  background: linear-gradient(135deg, #e55d87 0%, #5fc3e4 100%);
  .center {
    height: 100% !important;
    .title {
      font-size: 50px !important;
    }
  }
  .link {
    font-size: 20px;
    text-decoration: none;
    color: black;
    font-weight: 600;
  }
  .link:hover {
    padding-bottom: 3px;
    border-bottom: 2px black solid;
  }
}
</style>

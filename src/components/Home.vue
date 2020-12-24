<template>
	<div>
		<NavBar />
		<div class="">{{ this.$route.name }}</div>
		<Footer />
	</div>
</template>

<script>
import NavBar from './NavBar.vue';
import Footer from './Footer.vue';
export default {
	name: 'Home',
	components: { NavBar, Footer }
};
</script>

<style lang="scss" scoped>
</style>
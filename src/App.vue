<template>
	<v-app id="app">
		<NavBar />
		<v-main>
			<router-view :key="$route.fullPath" />
		</v-main>
		<Footer class="footer" />
		<vue-snotify></vue-snotify>
	</v-app>
</template>

<script>
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
export default {
	name: 'App',
	components: { NavBar, Footer }
};
</script>

<style lang="scss">
@import './style/globals';

* {
	margin: 0;
	padding: 0;
}
#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	.footer {
		position: fixed;
		bottom: 0;
		width: 100%;
	}
}
</style>

<template>
	<div id="app">
		<router-view />
		<vue-snotify></vue-snotify>
	</div>
</template>

<script>
export default {
	name: 'App',
	components: {}
};
</script>

<style lang="scss">
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
}
</style>

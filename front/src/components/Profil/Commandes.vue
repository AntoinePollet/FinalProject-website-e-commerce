<template>
	<div class="navigation">
		<ProfilNavigation />
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'Commandes',
	components: { ProfilNavigation }
};
</script>

<style lang="scss" scoped>
.navigation {
	height: 100%;
}
</style>
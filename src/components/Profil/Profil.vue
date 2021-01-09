<template>
	<div class="navigation">
		<ProfilNavigation />
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'Profil',
	components: { ProfilNavigation }
};
</script>

<style lang="scss" scoped>
.navigation {
	height: 100%;
}
</style>
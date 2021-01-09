<template>
	<div class="navigation">
		<ProfilNavigation />
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'CartesPaiement',
	components: { ProfilNavigation }
};
</script>

<style lang="scss" scoped>
.navigation {
	height: 100%;
}
</style>
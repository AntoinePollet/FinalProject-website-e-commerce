<template>
	<div v-if="!isAuth">
		<v-main>
			<p>
				<span
					@click="$router.push({ name: 'signin' })"
					class="text-decoration-underline"
					>Vous devez vous connectez pour continuer</span
				>
			</p>
		</v-main>
	</div>
	<div v-else class="navigation">
		<ProfilNavigation />
	</div>
</template>

<script>
import { mapState } from 'vuex';
import ProfilNavigation from '../../components/Profil/ProfilNavigation.vue';
export default {
	name: 'Profil',
	components: { ProfilNavigation },
	computed: {
		...mapState({
			isAuth: state => state.user.isAuth
		})
	}
};
</script>

<style lang="scss" scoped>
</style>
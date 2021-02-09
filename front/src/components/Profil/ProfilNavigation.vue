<template>
	<div class="col-lg-3 col-4 navigation" height="100px">
		<h2 v-for="item in profilItems" :key="item.name" class="navigation-items">
			<router-link
				:to="item.path"
				class="navigation-items-link"
				:active="true"
				>{{ item.name }}</router-link
			>
		</h2>
		<h2 v-if="isAdmin" class="navigation-items">
			<router-link
				to="/profil/admin"
				class="navigation-items-link"
				:active="true"
				>Admin
			</router-link>
		</h2>
		<v-btn text class="teal white--text" @click="signOut">Se deconnecter</v-btn>
	</div>
</template>

<script>
import { mapState } from 'vuex';
export default {
	name: 'ProfilNavigation',
	data() {
		return {
			profilItems: [
				{
					name: 'Mes commandes',
					path: '/profil/commandes'
				},
				{
					name: 'Mes informations',
					path: '/profil/informations'
				},
				{
					name: 'Mot de passe',
					path: '/profil/motdepasse'
				},
				{
					name: 'Mes cartes de paiement',
					path: '/profil/cartesdepaiement'
				}
			]
		};
	},
	computed: {
		...mapState({
			admin: state => state.user.role
		}),
		isAdmin() {
			if (this.admin.includes('admin')) {
				return true;
			} else return false;
		}
	},
	methods: {
		signOut() {
			if (this.$store.state.user.isAuth) {
				this.$store.dispatch('user/logout');
				this.$router.push('/');
			}
		}
	}
};
</script>

<style lang="scss" scoped>
.navigation {
	height: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-around;
	box-shadow: 5px 0 5px -5px #333;
	.navigation-items {
		.navigation-items-link {
			text-decoration: none;
			color: #2a9d8f;
		}
		.router-link-active {
			border-bottom: 2px solid #2a9d8f;
		}
	}
}
</style>
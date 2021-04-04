<template>
	<div class="navigation-bar blue-grey lighten-5 d-flex justify-center">
		<div class="navigation col-8 d-flex justify-space-around align-center">
			<p
				v-for="item in profilItems"
				:key="item.name"
				class="navigation-items mb-0"
			>
				<router-link
					:to="item.path"
					class="navigation-items-link"
					:active="true"
					>{{ item.name }}</router-link
				>
			</p>
			<p v-if="isAdmin" class="navigation-items mb-0">
				<router-link
					to="/profil/admin"
					class="navigation-items-link"
					:active="true"
					>Admin
				</router-link>
			</p>
		</div>
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
			role: state => state.user.role
		}),
		isAdmin() {
			if (this.role.includes('ROLE_ADMIN')) {
				return true;
			} else return false;
		}
	},
	methods: {}
};
</script>

<style lang="scss" scoped>
.navigation-bar {
	height: 75px;
	box-shadow: 5px 0 5px -5px #333;
	.navigation {
		.navigation-items {
			.navigation-items-link {
				font-weight: bold;
				text-decoration: none;
				color: $pink;
			}
		}
	}
}
.router-link-exact-active {
	border-bottom: 2px solid #a0c4ff;
}
</style>
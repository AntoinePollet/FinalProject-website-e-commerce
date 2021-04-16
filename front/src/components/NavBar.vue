<template>
	<v-app-bar app class="navbar">
		<v-toolbar-title
			><router-link to="/" class="navigation-home white--text"
				><v-img
					:src="image"
					contain
					max-height="45"
					max-width="45"
				/> </router-link
		></v-toolbar-title>
		<v-spacer></v-spacer>
		<v-autocomplete
			class="colorInput"
			v-model="searchInput"
			placeholder="Search"
			clearable
			@keyup.enter="searchItem(search)"
			hide-details
			outlined
			flat
			dense
			:items="items"
			hide-no-data
			:search-input.sync="search"
			solo
			color="white"
		/>
		<v-spacer></v-spacer>

		<v-menu v-if="isAuth" content-class="menu-list">
			<template v-slot:activator="{ on, attrs }">
				<p
					v-on="on"
					v-bind="attrs"
					class="navigation-list-items mb-0 mr-3 d-flex align-center"
					to="/profil"
				>
					{{ username }}
				</p>
			</template>
			<v-list>
				<v-list-item
					v-for="item in profil"
					:key="item.name"
					@click="$router.push(item.path)"
				>
					<v-icon>{{ item.mdi }}</v-icon>
					<v-list-item-title>{{ item.name }} </v-list-item-title>
				</v-list-item>
				<v-list-item v-if="isAdmin" @click="$router.push('/profil/admin')">
					<v-icon>mdi-account</v-icon>
					<v-list-item-title>Admin</v-list-item-title>
				</v-list-item>
				<v-list-item @click="logout">
					<v-icon>mdi-logout</v-icon>
					<v-list-item-title>Déconnexion</v-list-item-title>
				</v-list-item>
			</v-list>
		</v-menu>

		<router-link
			to="/favoris"
			class="navigation-list-items mb-0 mr-3 d-flex align-center"
			text
		>
			<div class="d-flex justify-center">
				<v-icon>mdi-heart</v-icon>({{ favorites.length }})
			</div>
		</router-link>
		<router-link
			to="/commande"
			class="navigation-list-items mb-0 mr-3 d-flex align-center"
			text
		>
			<v-icon>mdi-cart</v-icon>({{ totalItems }})
		</router-link>

		<v-divider vertical light inset v-if="!isAuth"></v-divider>

		<v-dialog v-if="!isAuth" v-model="dialogSignin" width="500">
			<template v-slot:activator="{ on, attrs }">
				<p class="black--text mb-0 mr-2 ml-3" text v-on="on" v-bind="attrs">
					Sign in
				</p>
			</template>
			<Signin @signup="signupDialog" @closeSignin="closeSignin" />
		</v-dialog>

		<v-dialog v-if="!isAuth" v-model="dialogSignup" width="500">
			<template v-slot:activator="{ on, attrs }">
				<p
					class="signup-text white--text mb-0 ml-2 py-2 px-2"
					text
					v-on="on"
					v-bind="attrs"
				>
					Sign up
				</p>
			</template>
			<Signup @signin="signinDialog" />
		</v-dialog>
	</v-app-bar>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import Signin from './ModalSignin.vue';
import Signup from './Signup.vue';
import items from '../data/items.json';
export default {
	name: 'NavBar',
	items: items,
	components: { Signin, Signup },
	data() {
		return {
			search: '',
			dialogSignin: false,
			dialogSignup: false,
			searchInput: '',
			items: [],
			profil: [
				{
					name: 'Mes commandes',
					path: '/profil/commandes',
					mdi: 'mdi-book'
				},
				{
					name: 'Mes informations',
					path: '/profil/informations',
					mdi: 'mdi-book-information-variant'
				},
				{
					name: 'Mot de passe',
					path: '/profil/motdepasse',
					mdi: 'mdi-lock'
				},
				{
					name: 'Mes cartes bancaires',
					path: '/profil/cartesdepaiement',
					mdi: 'mdi-bank'
				}
			]
		};
	},
	watch: {
		search(val) {
			if (val && val.length >= 2) {
				this.changeList(val);
			} else {
				this.items = [];
			}
		}
	},
	computed: {
		...mapState({
			cart: state => state.cart.cart,
			isAuth: state => state.user.isAuth,
			username: state => state.user.username,
			favorites: state => state.favorites.favoris,
			role: state => state.user.role
		}),
		...mapGetters({
			autocompleteList: 'cart/autocompleteList'
		}),
		image() {
			return require('../assets/KyoKyu.png');
		},
		isAdmin() {
			if (this.role.includes('ROLE_ADMIN')) {
				return true;
			} else return false;
		},
		totalItems() {
			return this.cart.reduce((acc, item) => {
				acc = acc + item.quantity;
				return acc;
			}, 0);
		}
	},
	methods: {
		searchItem(item) {
			if (this.$route.query.name !== item) {
				this.$router.replace({ path: '/search', query: { name: item } });
			}
		},
		changeList(item) {
			this.items = this.autocompleteList
				.filter(e => {
					return (
						(e || '').toLowerCase().indexOf((item || '').toLowerCase()) > -1
					);
				})
				.slice(0, 5);
		},
		logout() {
			if (this.$store.state.user.isAuth) {
				this.$store.dispatch('user/logout');
				//this.$router.push('/');
			}
		},
		signinDialog() {
			(this.dialogSignin = true), (this.dialogSignup = false);
		},
		signupDialog() {
			(this.dialogSignin = false), (this.dialogSignup = true);
		},
		closeSignin() {
			this.dialogSignin = false;
		}
	}
};
</script>

<style lang="scss" scoped>
.navbar {
	list-style: none;
	.v-select__selection,
	.v-select__selection--comma,
	.v-select.v-text-field input {
		color: black !important;
	}
	.navigation-home {
		text-decoration: none;
		font-weight: bold;
	}
	.navigation-list-items {
		height: inherit;
		color: rgb(0, 0, 0, 0.75);
		text-decoration: none;
	}
	.navigation-list-items:hover {
		color: rgb(0, 0, 0);
	}
	.navigation-list-items:focus {
		color: rgb(0, 0, 0);
	}
}
.menu-list {
	margin-top: 45px;
}
.signup-text {
	background-color: #48cae4;
	border-radius: 12px;
}
.router-link-exact-active {
	box-shadow: #48cae4 0 2px 0 0, inset #48cae4 0 -1px 0 0;
}
</style>

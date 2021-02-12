<template>
	<v-app-bar app class="navbar" :src="imageBackground">
		<v-toolbar-title
			><router-link to="/" class="navigation-home white--text"
				><v-img
					:src="image"
					contain
					max-height="45"
					max-width="45"
				></v-img></router-link
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
		<v-btn
			v-if="!isAuth"
			class="navigation-list-items white--text"
			to="/signin"
			text
			>Sign in
		</v-btn>
		<v-btn
			v-for="item in navigationBar"
			:key="item.path"
			class="navigation-list-items white--text"
			:to="item.path"
			text
		>
			{{ item.name }}
		</v-btn>
		<v-btn to="/favoris" class="navigation-list-items white--text" text>
			Favoris({{ favoris.length }})</v-btn
		>
		<v-btn to="/commande" class="navigation-list-items white--text" text>
			Cart({{ totalItems }})
		</v-btn>
	</v-app-bar>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import items from '../data/items.json';
export default {
	name: 'NavBar',
	items: items,
	data() {
		return {
			search: '',
			image: require('../public/fuji.png'),
			imageBackground: require('../public/discover.png'),
			searchInput: '',
			items: [],
			navigationBar: [
				{
					name: 'Profil',
					path: '/profil'
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
			favoris: state => state.favoris.favoris
		}),
		...mapGetters({
			autocompleteList: 'cart/autocompleteList'
		}),
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
		}
	}
};
</script>

<style lang="scss">
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
		text-decoration: none;
		color: white;
		margin-right: 15px;
		padding-bottom: 3px;
	}
	.navigation-list-items:hover {
		border-bottom: 2px solid white;
	}
}
</style>

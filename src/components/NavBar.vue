<template>
	<v-app-bar app class="navbar">
		<v-toolbar-title
			><router-link to="/" class="navigation-home white--text"
				>Home</router-link
			></v-toolbar-title
		>
		<v-spacer></v-spacer>
		<v-autocomplete
			class="colorInput"
			v-model="searchInput"
			placeholder="Search"
			clearable
			@keyup.enter="searchItem(search)"
			hide-details
			outlined
			dense
			:items="items"
			hide-no-data
			:search-input.sync="search"
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
		<v-btn to="/cart" class="navigation-list-items white--text" text>
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
			isAuth: state => state.user.isAuth
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
	background: linear-gradient(0.25turn, #2a9d8f, #0d312c);
	.v-select__selection,
	.v-select__selection--comma,
	.v-select.v-text-field input {
		color: white !important;
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

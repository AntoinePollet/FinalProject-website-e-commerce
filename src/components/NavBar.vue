<template>
	<v-app-bar app class="navbar">
		<v-toolbar-title
			><router-link to="/" class="navigation-home"
				>Home</router-link
			></v-toolbar-title
		>
		<v-spacer></v-spacer>
		<v-btn
			v-for="item in navigationBar"
			:key="item.path"
			class="navigation-list-items"
			:to="item.path"
			text
		>
			{{ item.name }}
		</v-btn>
		<v-btn to="/cart" class="navigation-list-items" text>
			Cart({{ totalItems }})
		</v-btn>
	</v-app-bar>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
export default {
	name: 'NavBar',
	data() {
		return {
			number: 0,
			navigationBar: [
				{
					name: 'Sign In',
					path: '/signIn'
				},
				{
					name: 'Profil',
					path: '/profil'
				}
			]
		};
	},
	computed: {
		...mapState({
			cart: state => state.cart.cart
		}),
		totalItems() {
			return this.cart.reduce((acc, item) => {
				acc = acc + item.quantity;
				return acc;
			}, 0);
		}
	}
};
</script>

<style lang="scss" scoped>
.navbar {
	list-style: none;
	background: linear-gradient(0.25turn, #2a9d8f, #0d312c);
	.navigation-home {
		text-decoration: none;
		color: white;
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

<template>
	<div class="navbar">
		<ul class="navigation-list">
			<li
				v-for="item in navigationBar"
				:key="item.path"
				class="navigation-list-items"
			>
				<router-link :to="item.path" class="navigation-list-items-link">{{
					item.name
				}}</router-link>
			</li>
		</ul>
	</div>
</template>

<script>
export default {
	name: 'NavBar',
	components: {},
	data() {
		return {
			navigationBar: [
				{
					name: 'Sign In',
					path: '/signIn'
				},
				{
					name: 'Profil',
					path: '/profil'
				},
				{
					name: 'Panier',
					path: '/panier'
				}
			]
		};
	}
};
</script>

<style lang="scss" scoped>
.navigation-list {
	display: flex;
	justify-content: flex-end;
	list-style: none;
	padding: 15px;
	background: linear-gradient(0.25turn, #2a9d8f, #0d312c);
	.navigation-list-items {
		font-size: 20px;
		.navigation-list-items-link {
			text-decoration: none;
			color: white;
			margin-right: 15px;
		}
		.navigation-list-items-link:hover {
			text-transform: uppercase;
		}
	}
}
</style>

<template>
	<v-footer class="footer d-flex justify-center" app>
		<ul class="navigation-list">
			<li
				v-for="item in navigationFooter"
				:key="item.path"
				class="navigation-list-items"
			>
				<router-link
					:to="item.path"
					:active="isActive"
					class="navigation-list-items-link"
					>{{ item.name }}</router-link
				>
			</li>
		</ul>
	</v-footer>
</template>

<script>
export default {
	name: 'Footer',
	components: {},
	data() {
		return {
			isActive: true,
			navigationFooter: [
				{
					name: 'About us',
					path: '/aboutus'
				},
				{
					name: 'Contact us',
					path: '/contact'
				},
				{
					name: 'Copyright',
					path: '/copyright'
				}
			]
		};
	}
};
</script>

<style lang="scss" scoped>
.footer {
	padding: 25px 0 25px 0;
	background-image: url(../public/sakura.jpg);
	.navigation-list {
		display: flex;
		justify-content: center;
		list-style: none;
		padding: 15px;
		.navigation-list-items {
			font-size: 20px;
			.navigation-list-items-link {
				text-decoration: none;
				margin-right: 15px;
				color: white;
				padding-bottom: 3px;
			}
			.navigation-list-items-link:hover {
				border-bottom: 2px solid white;
			}
			.router-link-active {
				border-bottom: 2px solid white;
			}
		}
	}
}
</style>
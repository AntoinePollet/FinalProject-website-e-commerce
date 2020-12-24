<template>
	<div class="footer">
		<ul class="navigation-list">
			<li
				v-for="item in navigationFooter"
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
	name: 'Footer',
	components: {},
	data() {
		return {
			navigationFooter: [
				{
					name: 'About us',
					path: '/aboutus'
				},
				{
					name: 'Contact us',
					path: '/contactus'
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
	background: linear-gradient(0.25turn, #2a9d8f, #0d312c);
	.navigation-list {
		display: flex;
		justify-content: center;
		list-style: none;
		padding: 15px;
		.navigation-list-items {
			font-size: 20px;
			.navigation-list-items-link {
				text-decoration: none;
				color: white;
				margin-right: 15px;
				padding-bottom: 3px;
			}
			.navigation-list-items-link:hover {
				border-bottom: 2px solid white;
			}
		}
	}
}
</style>
<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container>
			<v-breadcrumbs>Home</v-breadcrumbs>
			{{ commands }}
			<div v-if="commands.length !== 0">
				<h1>Vos commandes</h1>
				<div v-for="item in commands" :key="item.id">
					<div>
						<p>Id Commande {{ item.id }}</p>
					</div>
					<div>Articles{{ item.idArticle }}</div>
				</div>
			</div>
			<div v-else>Pas de commandes</div>
		</v-container>
		{{ order }}
	</div>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'Commandes',
	components: { ProfilNavigation },
	computed: {
		...mapState({
			username: state => state.user.username,
			commands: state => state.user.commands
		}),
		...mapGetters({
			asOrderWants: 'user/asOrderWants'
		}),
		order() {
			return this.asOrderWants('6074a05215f2630cbc62b791');
		}
	}
};
</script>

<style lang="scss" scoped>
</style>
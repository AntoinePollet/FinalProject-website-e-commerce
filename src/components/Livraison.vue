<template>
	<v-main>
		<v-container class="col-lg-8" v-if="isAuth">
			<v-card>
				<v-toolbar>
					<v-toolbar-title>Livraison</v-toolbar-title>
				</v-toolbar>
				<v-form v-model="valid" ref="form">
					<v-col class="d-flex justify-center flex-column">
						<v-row class="mx-3"
							><v-text-field placeholder="Nom"></v-text-field
							><v-spacer></v-spacer>
							<v-text-field placeholder="Prénom"></v-text-field
						></v-row>
						<v-row class="mx-3">
							<v-text-field placeholder="Adresse"></v-text-field>
						</v-row>

						<v-row class="mx-3">
							<v-text-field placeholder="Code postal"></v-text-field>
							<v-spacer></v-spacer>
							<v-text-field placeholder="Ville"></v-text-field
						></v-row>
					</v-col>
				</v-form>
			</v-card>
		</v-container>
		<div v-else>
			Vous devez vous connecter pour continuer,
			<v-btn text @click="signIn" class="teal white--text"
				>vous connecter</v-btn
			>
		</div>
	</v-main>
</template>

<script>
import { mapState } from 'vuex';
export default {
	name: 'livraison',
	components: {},
	data() {
		return {
			valid: true
		};
	},
	computed: {
		...mapState({
			isAuth: state => state.user.isAuth
		})
	},
	methods: {
		signIn() {
			this.$router.push('/signin');
		}
	}
};
</script>

<style lang="scss" scoped>
</style>
<template>
	<v-main>
		<v-container class="col-lg-8" v-if="isAuth">
			<v-card flat>
				<v-card-title>Livraison</v-card-title>
				<v-form v-model="valid" ref="form">
					<v-col class="d-flex justify-center flex-column">
						<v-text-field placeholder="Nom et Prénom" outlined dense />
						<v-text-field placeholder="Adresse" outlined dense />
						<v-text-field placeholder="Code postal" outlined dense />
						<v-text-field placeholder="Ville" outlined dense />
						<v-text-field
							placeholder="numéro de téléphone"
							outlined
							dense
							hint="Peut être utilisé pour faciliter le livraison"
							persistent-hint
						/>
						<v-checkbox
							label="Utiliser comme mon adresse par défaut."
							color="teal"
							v-model="defaultAdress"
						></v-checkbox>
					</v-col>
					<v-card-actions class="right"
						><v-btn
							:to="{ name: 'paiement' }"
							class="pink lighten-2 white--text"
							>suivant</v-btn
						>
					</v-card-actions>
				</v-form>
			</v-card>
		</v-container>
		<div v-else>
			<SigninComponent />
		</div>
	</v-main>
</template>

<script>
import { mapState } from 'vuex';
import SigninComponent from './SigninComponent.vue';

export default {
	name: 'livraison',
	components: { SigninComponent },
	data() {
		return {
			valid: true,
			defaultAdress: false
		};
	},
	computed: {
		...mapState({
			isAuth: state => state.user.isAuth
		})
	}
};
</script>

<style lang="scss" scoped>
</style>
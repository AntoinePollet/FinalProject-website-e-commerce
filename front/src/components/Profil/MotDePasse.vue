<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container class="col-lg-6 mt-8">
			<v-card flat>
				<h2 class="pt-5">Modifier mon mot de passe</h2>
				<v-form ref="form" v-model="validForm" class="pa-5">
					<p class="text-left mb-1">Nouveau mot de passe</p>
					<v-text-field
						type="password"
						v-model="newPassword"
						:rules="newPasswordRule"
						outlined
						dense
					></v-text-field>
					<p class="text-left mb-1">Réécrire nouveau mot de passe</p>
					<v-text-field
						type="password"
						v-model="retypePassword"
						:rules="retypePasswordRule"
						outlined
						dense
					></v-text-field>

					<v-card-actions class="justify-end"
						><v-btn class="pink lighten-2 white--text" @click="save"
							>Modifier mot de passe</v-btn
						></v-card-actions
					>
				</v-form>
			</v-card>
		</v-container>
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'MotDePasse',
	components: { ProfilNavigation },
	data() {
		return {
			validForm: true,
			newPassword: '',
			retypePassword: '',
			newPasswordRule: [v => !!v || 'Le champ ne peut pas être vide'],
			retypePasswordRule: [v => !!v || 'Le champ ne peut pas être vide']
		};
	},
	methods: {
		save() {
			if (this.$refs.form.validate()) {
				if (this.newPassword.localeCompare(this.retypePassword) === 0) {
					console.log('dispatch method');
				}
			}
		}
	}
};
</script>

<style lang="scss" scoped>
</style>
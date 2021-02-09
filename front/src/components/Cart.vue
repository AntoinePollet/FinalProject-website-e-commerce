<template>
	<v-main>
		<div v-if="cart.length != 0" id="element-to-print">
			<v-col>
				<v-row class="ma-5 d-flex flex-column align-content-end">
					<h2>
						Total price : <strong>{{ totalPrice }}</strong> €
					</h2>
					<v-btn
						class="teal"
						@click="$router.push({ name: 'livraison' })"
						text
						color="white"
						>Suivant</v-btn
					>
					<v-btn class="teal" @click="pdf()" text color="white">download</v-btn>
				</v-row>
				<v-row>
					<v-container class="d-flex flex-column justify-end align-end">
						<v-card
							v-for="(item, index) in cart"
							:key="index"
							class="col-lg-7 col-sm-12 mb-10"
						>
							<v-card-title
								>{{ item.name }}
								<v-btn
									:class="colorItem(item.couleur)"
									class="ml-5"
									rounded
								></v-btn
								><v-spacer></v-spacer>{{ item.price }} €</v-card-title
							>
							<div class="d-flex">
								<v-img :src="item.images[0]" width="100px" />

								<v-card-text>{{ item.description }}</v-card-text>
								<v-select
									:items="[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
									persistent-hint
									hint="Quantity"
									v-model="item.quantity"
								/>
							</div>
							<v-card-actions class="justify-end"
								><v-btn @click="removeItem(item, index)"
									>Supprimer</v-btn
								></v-card-actions
							>
						</v-card>
					</v-container>
				</v-row>
			</v-col>
		</div>

		<div v-else>Pas d'item dans le Panier</div>
	</v-main>
</template>

<script>
import { mapGetters } from 'vuex';
import colors from '../data/colors.json';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';

export default {
	name: 'Cart',
	data() {
		return {
			colors: colors
		};
	},
	computed: {
		...mapGetters({
			cart: 'cart/getCart'
		}),
		totalPrice() {
			return this.cart.reduce((acc, item) => {
				acc = acc + item.price * item.quantity;
				return acc;
			}, 0);
		},
		getCartItem() {
			return this.cart.map(item => {
				let arr = new Array();
				arr.push(
					item.name,
					item.categorie,
					item.quantity,
					item.couleur,
					item.price + ' €'
				);
				return arr;
			});
		}
	},
	methods: {
		removeItem(item, index) {
			this.$store.dispatch('cart/removeCartItem', index);
		},
		priceItem(item) {
			return item.price * item.quantity;
		},
		colorItem(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		},
		pdf() {
			const doc = new jsPDF();

			doc.text('KyoKyu', 100, 10);
			doc.setFontSize(12);
			doc.text('Name', 40, 20);
			doc.text('Adresse', 40, 25);
			doc.text('Postal code + city', 40, 30);
			doc.text('Pays', 40, 35);
			doc.setFontSize(16);
			doc.autoTable({
				startY: 50,
				head: [['Item', 'Categorie', 'quantity', 'Color', 'Price']],
				body: this.getCartItem
			});
			doc.text(
				`Total :  ${this.totalPrice} €`,
				100,
				doc.lastAutoTable.finalY + 10
			);
			doc.save('facture.pdf');
		}
	}
};
</script>

<style lang="scss" scoped>
</style>
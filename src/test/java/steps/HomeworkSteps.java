package steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Category;
import models.Produce;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class HomeworkSteps {

    List<Produce> firstList;
    List<Produce> secondList;
    List<Produce> produceDiscrepancies;
    List<Produce> produceDiscrepanciesFromTheFirstList;

    @DataTableType
    public Produce produce(Map<String, String> entry) {
        return Produce.builder().name(entry.get("name"))
                .price(Double.valueOf(entry.get("price")))
                .category(Category.valueOf(entry.get("category"))).build();
    }

    @Given("I have the following items in the first list:")
    public void iHaveTheFollowingItemsInTheFirstList(List<Produce> list) {
        firstList = list;
    }

    @And("I have the following items in the second list:")
    public void iHaveTheFollowingItemsInTheSecondList(List<Produce> list) {
        secondList = list;
    }

    @When("I compare both lists")
    public void iCompareBothLists() {
        produceDiscrepancies = secondList.stream()
                .filter(e -> !firstList.contains(e)).collect(Collectors.toList());
        produceDiscrepanciesFromTheFirstList = firstList.stream()
                .filter(e -> !secondList.contains(e)).collect(Collectors.toList());
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void theListsShouldContainTheSameItemsWithNamePriceAndCategoryRegardlessOfOrder() {
        for (int i = 0; i < produceDiscrepancies.size(); i++) {
            Assert.assertEquals(
                    "\nFound discrepancies in the " +
                            produceDiscrepanciesFromTheFirstList.get(i).compare(produceDiscrepancies.get(i))+
                    " column for the produce described below:",
                    produceDiscrepancies.get(i),
                    produceDiscrepanciesFromTheFirstList.get(i));
        }
    }
}

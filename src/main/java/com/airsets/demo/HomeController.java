package com.airsets.demo;

import com.airsets.demo.DAO.ProductDAOImpl;
import com.airsets.demo.models.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

import lombok.RequiredArgsConstructor;
import okhttp3.RequestBody;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RequiredArgsConstructor
@Component
@Controller
public class HomeController {
    private final ProductDAOImpl products;
    @GetMapping("/")
    public String index(Model model, @CookieValue(value = "product") String cookies) {
        String[] carts = cookies.split(":");
        System.out.println(":");
        model.addAttribute("products", products.getAllProducts());
        model.addAttribute("cart", carts.length);
        return "index";
    }

    @GetMapping("/store")
    public String store(Model model) {
        model.addAttribute("products", products.getAllProducts());
        return "store";
    }
    @GetMapping("/privacy-policy")
    public String privacy_policy(Model model) {
        return "privacy_policy";
    }
    @GetMapping("/refund-returns")
    public String refund_return(Model model) {
        return "refund_return";
    }

    @GetMapping("/product/{id}")
    public String product(Model model,@PathVariable String id) {
        Product c = products.getProductById(Integer.parseInt(id));
        c.getProduct_Name();
        model.addAttribute("products", products.getProductById(Integer.parseInt(id)));
        System.out.println(id);
        return "product";
    }
    @RequestMapping(value="/add/{id}",method=RequestMethod.POST)
    public String add(HttpServletRequest request, Model model) {
        request.getCookies();
        return "store";
    }
    @RequestMapping("/checkout")
    public String checkout(Model model, @CookieValue(value = "product") String cookies) {
        String[] carts = cookies.split(":");
        System.out.println(":");
        List<Product> carts_list = new ArrayList<>();
        int total = 0;
        for (int i =1;i<carts.length;i++){
            total += products.getProductById(Integer.parseInt(carts[i])).getPrice();
            carts_list.add(products.getProductById(Integer.parseInt(carts[i])));
        }
        model.addAttribute("products", carts_list);
        model.addAttribute("total", total);
        return "checkout";

    }
    @GetMapping("/k")
    public ModelAndView indexes(@CookieValue(value = "product") String cookies) throws IOException {
        String[] carts = cookies.split(":");
        System.out.println(":");
        int total = 0;
        for (int i =1;i<carts.length;i++){
            total += products.getProductById(Integer.parseInt(carts[i])).getPrice();
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"business_name\":\"AirSets\",\"customer_email\":\"whatop002@gmail.com\",\"customer_name\":\"Client\",\"memo\":\"\",\"local_price\":{\"amount\":\"%s\",\"currency\":\"USD\"}}",total));
        Request request = new Request.Builder()
                .url("https://api.commerce.coinbase.com/invoices")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("X-CC-Version", "2018-03-22")
                .addHeader("X-CC-Api-Key", "f0311ae3-ed9f-46a4-8d17-18a031464cf4")
                .build();
        Response responses = client.newCall(request).execute();
        String jsonData = responses.body().string();
        JSONObject Jobject = new JSONObject(jsonData);
        JSONObject data = Jobject.getJSONObject("data");
        String hosted_url= data.getString("hosted_url");
        System.out.println(hosted_url);
        return new ModelAndView("redirect:" + hosted_url);
    }
}

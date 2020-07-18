package com.workstore.admin.modules.product.api.request;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @AllArgsConstructor @Builder
public class ProductPayload {
	private String name;
	private String description;
	private String content;
	private AddressPayload address;
	private SeatInfoPayload seatInfo;
	private HostInfoPayload hostInfo;
	private SubscribePayload prices;
	private Set<ManageInfoPayload> manageInfos;
	private List<String> amenities;
	private List<String> cautionNotes;
	private List<ImagePayload> images;
	private List<String> tags;
}
